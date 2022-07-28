package com.cg.book.app.service;

/*********************************************************************************

 * Description : This is AppUserServiceImpl class
**********************************************************************************/
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.book.app.exception.AppUserAlreadyExistsException;
import com.cg.book.app.exception.AppUserNotFoundException;
import com.cg.book.app.model.AppUser;
import com.cg.book.app.repository.AppUserRepository;

@Service
public class AppUserServiceImpl implements AppUserService {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AppUserRepository appUserRepository;

	AppUser signedInUser;

	@Override
	public List<AppUser> getAllUsers() {
		List<AppUser> userList = appUserRepository.findAll();
		if (userList.isEmpty()) {
			String exceptionMessage = "AppUsers don't exist in the database.";
			LOG.warn(exceptionMessage);
			throw new AppUserNotFoundException(exceptionMessage);
		} else {
			LOG.info("List returned successfully.");
			return userList;
		}
	}

	@Override
	public AppUser signUpUser(AppUser appUser) {
		LOG.info(appUser.toString());
		Optional<AppUser> userOptional = appUserRepository.findById(appUser.getUserName());
		if (userOptional.isEmpty()) {
			return appUserRepository.save(appUser);
		} else {
			String exceptionMessage = "User with userName " + appUser.getUserName() + " already exists.";
			throw new AppUserAlreadyExistsException(exceptionMessage);
		}
	}

	@Override
	public AppUser signInUser(AppUser appUser) {
		LOG.info(appUser.toString());
		Optional<AppUser> userOptional = appUserRepository.findById(appUser.getUserName());
		if (userOptional.isPresent()) {
			if (appUser.equals(userOptional.get())) {
				LOG.info(userOptional.get().toString());
				signedInUser = appUser;
				return appUser;
			} else {
				String exceptionMessage = "Wrong password!";
				LOG.warn(exceptionMessage);
				throw new AppUserNotFoundException(exceptionMessage);
			}
		} else {
			String exceptionMessage = "Wrong userName!";
			LOG.warn(exceptionMessage);
			throw new AppUserNotFoundException(exceptionMessage);
		}
	}

	@Override
	public AppUser updateUser(AppUser appUser) {
		Optional<AppUser> userOptional = appUserRepository.findById(appUser.getUserName());
		if (userOptional.isPresent()) {
			LOG.info(userOptional.get().toString());
			return appUserRepository.save(appUser);
		} else {
			String exceptionMessage = "AppUser with userName " + appUser.getUserName() + " not found!";
			LOG.warn(exceptionMessage);
			throw new AppUserNotFoundException(exceptionMessage);
		}
	}

}